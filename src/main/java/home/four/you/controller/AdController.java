package home.four.you.controller;

import home.four.you.exception.ResourceNotFoundException;
import home.four.you.model.PropertyType;
import home.four.you.model.dto.AdBriefDetailsDto;
import home.four.you.model.dto.AdDetailsDto;
import home.four.you.model.dto.AdSearchFilter;
import home.four.you.model.dto.CreateAdRequestDto;
import home.four.you.model.dto.CreateAdResponseDto;
import home.four.you.model.entity.Ad;
import home.four.you.security.CurrentUser;
import home.four.you.security.UserPrincipal;
import home.four.you.security.auth.authorization.permission.CanDeleteAd;
import home.four.you.service.AdService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for {@link Ad} related operations.
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "api/ads")
public class AdController {

    private final AdService adService;
    private final ConversionService conversionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAdResponseDto createAd(@RequestBody @Valid CreateAdRequestDto dto,
                                        @CurrentUser UserPrincipal caller) {
        log.debug("Creating ad [{}]", dto);

        var ad = adService.createAd(dto, caller);

        return conversionService.convert(ad, CreateAdResponseDto.class);
    }

    @GetMapping("{id}")
    public AdDetailsDto getDetails(@PathVariable Long id) {
        log.debug("Finding ad with id {}", id);

        var ad = adService.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        return conversionService.convert(ad, AdDetailsDto.class);
    }

    @DeleteMapping("{id}")
    @CanDeleteAd
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.debug("Deleting ad {}", id);

        adService.delete(id);
    }

    @GetMapping("latest")
    public List<AdBriefDetailsDto> getLatest() {
        log.debug("Getting latest ads");

        return adService.findLatest().stream()
                .map(ad -> conversionService.convert(ad, AdBriefDetailsDto.class))
                .toList();
    }

    @GetMapping("search")
    public Page<AdBriefDetailsDto> search(@RequestParam(required = false) Ad.Type type,
                                          @RequestParam(required = false) Integer minPrice,
                                          @RequestParam(required = false) Integer maxPrice,
                                          @RequestParam(required = false) PropertyType propertyType,
                                          @RequestParam(required = false) Integer minArea,
                                          @RequestParam(required = false) Integer maxArea,
                                          @RequestParam(required = false) Integer minNumberOfRooms,
                                          @RequestParam(required = false) Integer maxNumberOfRooms,
                                          @PageableDefault Pageable pageable) {
    log.debug("Searching ads... page {}", pageable.getPageNumber());

        var filter = new AdSearchFilter(
                type,
                minPrice,
                maxPrice,
                propertyType,
                minArea,
                maxArea,
                minNumberOfRooms,
                maxNumberOfRooms);

        return adService.search(filter, pageable)
                .map(ad -> conversionService.convert(ad, AdBriefDetailsDto.class));
    }

}
