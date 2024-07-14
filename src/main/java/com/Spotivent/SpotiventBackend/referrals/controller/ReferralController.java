package com.Spotivent.SpotiventBackend.referrals.controller;

import com.Spotivent.SpotiventBackend.referrals.service.ReferralService;
import com.Spotivent.SpotiventBackend.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/referral")
@Validated
public class ReferralController {
    private final ReferralService referralService;

    public ReferralController(ReferralService referralService) {
        this.referralService = referralService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReferralByUserId(@PathVariable Long id) {
        return Response.success("Get referral success", referralService.getByUsersId(id));
    }

}