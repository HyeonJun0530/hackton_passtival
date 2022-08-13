package com.passtival.passtival.service;

import com.passtival.passtival.constant.FestivalStatus;
import com.passtival.passtival.domain.Festival;
import com.passtival.passtival.domain.FestivalImage;
import com.passtival.passtival.dto.CreateFestivalDto;
import com.passtival.passtival.dto.FestivalDetailDto;
import com.passtival.passtival.dto.FestivalPreviewDto;
import com.passtival.passtival.dto.FestivalSearchDto;
import com.passtival.passtival.repository.FestivalImageRepository;
import com.passtival.passtival.repository.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FestivalService {

    private final FestivalRepository festivalRepository;
    private final FestivalImageRepository festivalImageRepository;
    private final LocalFileService localFileService;

    /**
     * 행사 정보 등록 비즈니스
     */
    @Transactional
    public CreateFestivalDto.Response saveFestival(CreateFestivalDto.Request request) {
        Festival festival = Festival.builder()
                .title(request.getTitle())
                .host(request.getHost())
                .location(request.getLocation())
                .date(request.getDate())
                .content(request.getContent())
                .status(request.getStatus())
                .month(request.getMonth())
                .city(request.getCity())
                .isFree(request.getIsFree())
                .build();
        festivalRepository.save(festival);

        return CreateFestivalDto.Response.of(festival.getId());
    }

    /**
     * 행사 이미지 등록 비즈니스
     */
    @Transactional
    public FestivalDetailDto saveFestivalImage(Long festivalId, List<MultipartFile> imageList) throws Exception {
        Optional<Festival> festival = festivalRepository.findById(festivalId);

        for (int i = 0; i < imageList.size(); i++) {

            // file 업데이트
            if (i == 0 && imageList.get(i) == null)
                continue;

            MultipartFile imgFile = imageList.get(i);
            String originalImgName = imgFile.getOriginalFilename();
            String storedImgName = null;
            String storedImgUrl = null;

            // 파일 업로드 (로컬 리소스 스토리지)
            if (StringUtils.hasText(originalImgName)) {
                storedImgName = fileUploadToLocal(originalImgName, imgFile);
            }
            storedImgUrl = "/storage/images/item/" + storedImgName;

            System.out.println(storedImgName);
            System.out.println(originalImgName);
            System.out.println(storedImgUrl);

            // 상품 이미지의 정보를 DB에 저장
            FestivalImage festivalImage = FestivalImage.builder()
                    .originalImgName(originalImgName)
                    .storedImgName(storedImgName)
                    .imgUrl(storedImgUrl)
                    .festival(festival.get())
                    .build();

            festivalImageRepository.save(festivalImage);
        }

        return FestivalDetailDto.builder()
                .festivalId(festival.get().getId())
                .build();
    }

    // 로컬 폴더에 이미지 저장
    private String fileUploadToLocal(String originalImgName, MultipartFile itemImgFile) throws Exception {
        return localFileService.uploadFile(originalImgName, itemImgFile);
    }

    /**
     * 메인 페이지에 보여질 행사 리스트 정보를 반환하는 비즈니스
     * 행사 상태
     */
    public List<FestivalPreviewDto> getFestivalMainByStatus(FestivalStatus status) {
        List<Festival> festivals = festivalRepository.findAllByStatus(status);
        List<FestivalPreviewDto> mainPageDtos = new ArrayList<>();

        for (Festival festival : festivals) {
            FestivalImage festivalImage = festivalImageRepository.findByFestival(festival);
            mainPageDtos.add(
                    FestivalPreviewDto.builder()
                            .festivalId(festival.getId())
                            .festivalName(festival.getTitle())
                            .imgUrl(festivalImage.getImgUrl())
                            .build()
            );
        }

        return mainPageDtos;
    }

    /**
     * 상세 검색 페이지에 보여질 행사 리스트 정보를 반환하는 비즈니스
     * 행사 유/무료 여부 & 개최 구 & 개최 달
     */
    public List<FestivalPreviewDto> getFestivalSearch(FestivalSearchDto festivalSearchDto) {
        List<Festival> festivalList = festivalRepository.getSearchPage(festivalSearchDto);
        List<FestivalPreviewDto> previewDtos = new ArrayList<>();

        for (Festival festival : festivalList) {
            FestivalImage festivalImage = festivalImageRepository.findByFestival(festival);
            previewDtos.add(
                    FestivalPreviewDto.builder()
                            .festivalId(festival.getId())
                            .festivalName(festival.getTitle())
                            .imgUrl(festivalImage.getImgUrl())
                            .build()
            );
        }

        return previewDtos;
    }

    /**
     * 특정 행사 상세 정보 조회
     */
    public FestivalDetailDto getFestivalDetail(Long festivalId) {
        Festival festival = festivalRepository.findById(festivalId).get();


        return FestivalDetailDto.builder()
                .festivalId(festival.getId())
                .title(festival.getTitle())
                .host(festival.getHost())
                .location(festival.getLocation())
                .date(festival.getDate())
                .content(festival.getContent())
                .status(festival.getStatus())
                .month(festival.getMonth())
                .city(festival.getCity())
                .isFree(festival.getIsFree())
                .build();
    }
}
