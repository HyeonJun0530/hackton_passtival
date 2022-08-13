package com.passtival.passtival.controller;

import com.passtival.passtival.constant.FestivalStatus;
import com.passtival.passtival.controller.response.ListResult;
import com.passtival.passtival.controller.response.ResponseService;
import com.passtival.passtival.controller.response.SingleResult;
import com.passtival.passtival.domain.Festival;
import com.passtival.passtival.dto.CreateFestivalDto;
import com.passtival.passtival.dto.CreateFestivalImageResponseDto;
import com.passtival.passtival.dto.FestivalMainPageDto;
import com.passtival.passtival.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FestivalController {

    private final FestivalService festivalService;
    private final ResponseService responseService;

    /**
     * 행사 정보(텍스트) 등록 API
     */
    @PostMapping("/api/festival")
    public SingleResult<CreateFestivalDto.Response> saveFestival(@RequestBody CreateFestivalDto.Request request) {
        CreateFestivalDto.Response response = festivalService.saveFestival(request);

        return responseService.getSingleResult(
                HttpStatus.OK.value(),
                "성공적으로 행사 정보가 등록되었습니다.",
                response
        );
    }

    /**
     * 행사 이미지 등록 API
     */
    @PostMapping("/api/festival/{festivalId}/image")
    public SingleResult<CreateFestivalImageResponseDto> saveFestivalImage(@PathVariable Long festivalId,
                                                                          @RequestParam("imgFile") List<MultipartFile> imageList) throws Exception {

        if (imageList.get(0).isEmpty()) {
            throw new Exception();
        }

        CreateFestivalImageResponseDto response = festivalService.saveFestivalImage(festivalId, imageList);

        return responseService.getSingleResult(
                HttpStatus.OK.value(),
                "성공적으로 행사 이미지가 등록되었습니다.",
                response
        );
    }

    /**
     * 메인 페이지 조회 API
     */
    @GetMapping("/api/festival/main")
    public ListResult<FestivalMainPageDto> getFestivalMain(@RequestParam("status") String status) {

        System.out.println(status);
        FestivalStatus festivalStatus = null;
        if (status.equals("GOING")) {
            festivalStatus = FestivalStatus.GOING;
        } else if (status.equals("START")) {
            festivalStatus = FestivalStatus.START;
        }

        List<FestivalMainPageDto> festivalMainPageDtos = festivalService.getFestivalMainByStatus(festivalStatus);

        return responseService.getListResult(
                HttpStatus.OK.value(),
                "성공적으로 메인 페이지의 정보를 가져왔습니다.",
                festivalMainPageDtos
        );
    }
}
