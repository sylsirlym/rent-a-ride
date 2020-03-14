package com.skills.rentaride.controller;

import com.skills.rentaride.dtos.requests.LendRequestDTO;
import com.skills.rentaride.dtos.responses.ResponseDTO;
import com.skills.rentaride.exceptions.ItemNotFoundException;
import com.skills.rentaride.exceptions.LendTxnStatusNotFoundException;
import com.skills.rentaride.exceptions.ProfileNotFoundException;
import com.skills.rentaride.services.LendingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sylvester
 * Project rent-a-ride
 * User: sylvester
 * Date: 3/11/20
 * Time: 1:17 AM
 */
@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class LendingController {
    private LendingService lendingService;

    /**
     * Lend item response dto.
     *
     * @param lendRequestDTO the lend request dto
     * @return the response dto
     * @throws LendTxnStatusNotFoundException the lend txn status not found exception
     * @throws ProfileNotFoundException       the profile not found exception
     * @throws ItemNotFoundException          the item not found exception
     */
    @PostMapping("/rent-item/request")
    public ResponseDTO lendItem(@RequestBody LendRequestDTO lendRequestDTO) throws LendTxnStatusNotFoundException, ProfileNotFoundException, ItemNotFoundException {
        return lendingService.requestItem(lendRequestDTO);
    }
}
