package com.cgi.api.web.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Contains valid file formats that application can process.
 *
 * @author Aashish
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public enum FileType {

    CSV("csv", ';')
    ;

    private final String extension;
    private final char columnSeparator;

    public static FileType mapToType(String fileExtension) {
        return Arrays.stream(FileType.values())
                .filter(value -> value.getExtension().equalsIgnoreCase(fileExtension))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Unknown file extension."));
    }

    public static List<String> getFileTypes() {
        return Arrays.asList(values()).stream().map(FileType::getExtension).collect(Collectors.toList());
    }
}
