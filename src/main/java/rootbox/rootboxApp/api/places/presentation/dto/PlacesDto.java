package rootbox.rootboxApp.api.places.presentation.dto;

import lombok.*;

import java.util.List;

public class PlacesDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class NearByPlaceDto {

        private String placeName;

        private Float latitude;

        private Float longitude;

        private String openTime;

        private Boolean isOpen;

        private List<String> photoList;
    }
}
