package rootbox.rootboxApp.api.places.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rootbox.rootboxApp.api.places.business.PlacesService;
import rootbox.rootboxApp.api.places.presentation.dto.PlacesDto;
import rootbox.rootboxApp.global.common.CommonResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/places")
public class PlacesApi {

    private final PlacesService placesService;

    @GetMapping("/")
    public CommonResponse<List<PlacesDto.NearByPlaceDto>> getNearByPlaces(@RequestParam(required = false) Integer radius
            , @RequestParam(required = false) Integer size, @RequestParam(required = false) String nextPageToken
            , @RequestParam(required = true) String category
            , @RequestParam(required = true) double lat
            , @RequestParam(required = true) double lng) {
        return CommonResponse.onSuccess(placesService.getNearByPlaces(lat,lng,category,radius,size,nextPageToken));
    }

}
