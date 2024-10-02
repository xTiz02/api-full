package org.example.marvelapi.persistence.marvel.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.example.marvelapi.persistence.marvel.dto.CharacterDto;
import org.example.marvelapi.persistence.marvel.dto.ThumbnailDto;

import java.util.ArrayList;
import java.util.List;

public class CharacterMapper {

    public static List<CharacterDto> toList(JsonNode jsonNode) {

        List<CharacterDto> characterDtoList = new ArrayList<>();
        getArrayNode(jsonNode).elements().forEachRemaining(each->{
                characterDtoList.add(CharacterMapper.toDto(each));
        });
        return characterDtoList;
    }

    public static List<CharacterDto.CharacterInfoDto> toDtoList(JsonNode jsonNode) {
        List<CharacterDto.CharacterInfoDto> characterInfoDtoList = new ArrayList<>();
        getArrayNode(jsonNode).elements().forEachRemaining(each->{
            characterInfoDtoList.add(CharacterMapper.toDtoInfo(each));
        });
        return characterInfoDtoList;
    }

    private static CharacterDto toDto(JsonNode jsonNode) {
        if (jsonNode == null) {
            throw new IllegalArgumentException("El nodo json no puede ser nulo");
        }
        return new CharacterDto(
                jsonNode.get("id").asLong(),
                jsonNode.get("name").asText(),
                jsonNode.get("description").asText(),
                jsonNode.get("modified").asText(),
                jsonNode.get("resourceURI").asText());
    }

    private static CharacterDto.CharacterInfoDto toDtoInfo(JsonNode jsonNode) {
        if (jsonNode == null) {
            throw new IllegalArgumentException("El nodo json no puede ser nulo");
        }

        JsonNode thumbnail = jsonNode.get("thumbnail");

        ThumbnailDto thumbnailDto = ThumbnailMapper.toDto(thumbnail);

        String imagePath = thumbnailDto.path().concat(".").concat(thumbnailDto.extension());

        return new CharacterDto.CharacterInfoDto(
                imagePath,
                jsonNode.get("description").asText());
    }

    private static ArrayNode getArrayNode(JsonNode jsonNode) {
        if(jsonNode == null) {
            throw new IllegalArgumentException("El nodo json no puede ser nulo");
        }
        JsonNode data = jsonNode.get("data");
        return (ArrayNode) data.get("results");
    }
}
