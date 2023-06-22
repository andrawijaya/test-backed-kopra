package com.kopra.controller;

import com.kopra.entity.Profile;
import com.kopra.entity.request.ProfileCreateRequest;
import com.kopra.entity.response.ErrorResponse;
import com.kopra.entity.response.PagingResponse;
import com.kopra.entity.response.SuccessResponse;
import com.kopra.exception.NotFoundException;
import com.kopra.service.IProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    private IProfileService profileService;

    private ModelMapper modelMapper;

    @Autowired
    public ProfileController(IProfileService profileService, ModelMapper modelMapper){
        this.profileService = profileService;
        this.modelMapper = modelMapper;
    }

    // Get All Data
    @GetMapping
    public ResponseEntity getAllProfile(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "DESC") String direction,
            @RequestParam(defaultValue = "id") String sortBy
    ) throws Exception {
        Page<Profile> profiles = profileService.listProfile(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get profile list", profiles));
    }

    // Create Data
    @PostMapping
    public ResponseEntity createProfile(@Valid @RequestBody ProfileCreateRequest profileCreateRequest) throws Exception {
        System.out.println("entah");
        Profile profile = modelMapper.map(profileCreateRequest, Profile.class);
        Profile result = profileService.create(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success register profile", result));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity handleNotValidException(MethodArgumentNotValidException exception) {
        String msgStr = exception.getMessage();
        System.out.println(msgStr);
        List<String> msgList = Arrays.asList(msgStr.split("\\s*;\\s*"));
        System.out.println(msgList);
        String strErrorMsg = msgList.get(5);
        String[] listErrorMsg = strErrorMsg.split("\\[|\\]");
        String currentMsg = listErrorMsg[1];
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("400", currentMsg));
    }
}
