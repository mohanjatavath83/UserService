package com.userservice.model.media;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "medias")
public class MediaModel {

    @Id
    private String code;
    private String url;
    private String mime;
    private String fileName;
    private Long size;
}
