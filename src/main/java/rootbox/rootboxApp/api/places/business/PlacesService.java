package rootbox.rootboxApp.api.places.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rootbox.rootboxApp.api.places.implementation.PlacesCommandAdapter;
import rootbox.rootboxApp.api.places.implementation.PlacesQueryAdapter;
import rootbox.rootboxApp.api.places.presentation.dto.PlacesDto;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlacesService {

    private final PlacesQueryAdapter placesQueryAdapter;

    private final PlacesCommandAdapter placesCommandAdapter;

    public List<PlacesDto.NearByPlaceDto> getNearByPlaces(double latitude, double longitude
    ,String category, Integer radius, Integer size, String nextPageToken) {

        return null;
    }
}
