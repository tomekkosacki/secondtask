package com.comarch.tomasz.kosacki.mapper;

import com.comarch.tomasz.kosacki.tagDto.TagDto;
import com.comarch.tomasz.kosacki.tagEntity.TagEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class TagMapper {

    public TagMapper() {
    }

    public TagDto tagEntityToTagDto(TagEntity from) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(from, TagDto.class);
    }

    public TagEntity tagDtoToTagEntity(TagDto from){

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(from, TagEntity.class);
    }

    public List<TagDto> tagEntityListToTagDtoList(List<TagEntity> tagEntityList) {

        ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<TagDto>>() {}.getType();
        return modelMapper.map(tagEntityList, listType);
    }
}
