package com.web.roomiez.email;

import com.web.roomiez.user.CustomUserDetailsService;
import com.web.roomiez.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenService {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public ConfirmationTokenService() {
    }

    public void saveConfirmationToken(ConfirmationToken token){
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token){
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token){
        return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    @Transactional
    public String confirmToken(String token){
        ConfirmationToken confirmationToken = this.getToken(token).orElseThrow(()-> new IllegalStateException("token not found"));
        if (confirmationToken.getConfirmedAt() != null){
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("token expired");
        }

        this.setConfirmedAt(token);

        //TODO: enable user accounts after confirming
        //customUserDetailsService.enableAppUser(confirmationToken.getUser().getUsername());

        return "Confirmed";
    }
}
