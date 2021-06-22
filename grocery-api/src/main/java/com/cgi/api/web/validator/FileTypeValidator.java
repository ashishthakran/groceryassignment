package com.cgi.api.web.validator;

import com.cgi.api.web.annotation.ValidFileType;
import com.cgi.api.web.enums.FileType;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * This class contains file type constraints that will check which file formats are allowed.
 *
 * @author Aashish
 * @version 1.0
 */
public class FileTypeValidator implements ConstraintValidator<ValidFileType, MultipartFile> {

    private static final String ERR_INVALID_FILE_TYPE = "Only CSV files are allowed.";
    private static final List<String> VALID_FILE_FORMAT_LIST = FileType.getFileTypes();

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        boolean isValid = true;
        String fileExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        if (!VALID_FILE_FORMAT_LIST.contains(fileExtension)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ERR_INVALID_FILE_TYPE).addConstraintViolation();
            isValid = false;
        }

        return isValid;
    }
}
