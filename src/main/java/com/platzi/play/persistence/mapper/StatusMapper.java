package com.platzi.play.persistence.mapper;

import org.mapstruct.Named;

public class StatusMapper {

        @Named("stringToBoolean")
        public static Boolean stringToBoolean(String status) {
            if (status == null){
                return null;
            }

            return switch (status){
                case "D" -> true;
                default -> false;
            };
        }

        @Named("booleanToString")
        public static String booleanToString(Boolean status) {
            return status == null ? null : (status ? "D" : "N");
        }
}
