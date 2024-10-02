package org.example.marvelapi.persistence.entity.mapper;

import org.example.marvelapi.persistence.entity.UserInteractionLog;
import org.example.marvelapi.persistence.entity.dto.GetUserInteractionLogDto;

public class UserInteractionLogMapper {
    public static GetUserInteractionLogDto toDto(UserInteractionLog entity){

        if(entity == null) return null;

        return new GetUserInteractionLogDto(
                entity.getId(),
                entity.getUrl(),
                entity.getHttpMethod(),
                entity.getUsername(),
                entity.getTimestamp(),
                entity.getRemoteAddress()
        );

    }
}
