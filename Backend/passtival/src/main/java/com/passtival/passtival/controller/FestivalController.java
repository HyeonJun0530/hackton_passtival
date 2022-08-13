package com.passtival.passtival.controller;

import com.passtival.passtival.constant.FestivalCity;
import com.passtival.passtival.constant.FestivalMonth;
import com.passtival.passtival.constant.FestivalStatus;
import com.passtival.passtival.controller.response.ListResult;
import com.passtival.passtival.controller.response.ResponseService;
import com.passtival.passtival.controller.response.SingleResult;
import com.passtival.passtival.dto.CreateFestivalDto;
import com.passtival.passtival.dto.FestivalDetailDto;
import com.passtival.passtival.dto.FestivalPreviewDto;
import com.passtival.passtival.dto.FestivalSearchDto;
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
    public SingleResult<FestivalDetailDto> saveFestivalImage(@PathVariable Long festivalId,
                                                             @RequestParam("imgFile") List<MultipartFile> imageList) throws Exception {

        if (imageList.get(0).isEmpty()) {
            throw new Exception();
        }

        FestivalDetailDto response = festivalService.saveFestivalImage(festivalId, imageList);

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
    public ListResult<FestivalPreviewDto> getFestivalMain(@RequestParam("status") String status) {

        FestivalStatus festivalStatus = null;
        if (status.equals("GOING")) {
            festivalStatus = FestivalStatus.GOING;
        } else if (status.equals("START")) {
            festivalStatus = FestivalStatus.START;
        }

        List<FestivalPreviewDto> festivalPreviewDtos = festivalService.getFestivalMainByStatus(festivalStatus);

        return responseService.getListResult(
                HttpStatus.OK.value(),
                "성공적으로 메인 페이지의 정보를 가져왔습니다.",
                festivalPreviewDtos
        );
    }

    /**
     * 검색 페이지에서 행사 조회
     */
    @GetMapping("/api/festival/search")
    public ListResult<FestivalPreviewDto> getFestivalSearch(@RequestParam(value = "is-free", required = false) String isFree,
                                                            @RequestParam(value = "month", required = false) String month,
                                                            @RequestParam(value = "city", required = false) String city
                                                            ) {
        System.out.println(isFree);
        System.out.println("month : " + month);
        System.out.println("city : "+ city);

        Boolean free = (isFree.equals("true")) ? true : false;
        FestivalMonth m = (month == null) ? null : FestivalMonth.valueOf(month);
        FestivalCity c = (city == null) ? null : FestivalCity.valueOf(city);

        FestivalSearchDto festivalSearchDto = FestivalSearchDto.builder()
                .isFree(free)
                .month(m)
                .city(c)
                .build();

        List<FestivalPreviewDto> festivalPreviewDtos = festivalService.getFestivalSearch(festivalSearchDto);

        return responseService.getListResult(
                HttpStatus.OK.value(),
                "성공적으로 검색하였습니다.",
                festivalPreviewDtos
        );
    }

    /**
     * 상세 페이지 조회
     */
    @GetMapping("/api/festival/{festivalId}")
    public SingleResult<FestivalDetailDto> getFestivalDetail(@PathVariable Long festivalId) {
        FestivalDetailDto festivalDetail = festivalService.getFestivalDetail(festivalId);

        return responseService.getSingleResult(
                HttpStatus.OK.value(),
                "성공적으로 조회하였습니다.",
                festivalDetail
        );
    }
}
