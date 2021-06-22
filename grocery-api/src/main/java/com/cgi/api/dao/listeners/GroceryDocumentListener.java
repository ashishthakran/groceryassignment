package com.cgi.api.dao.listeners;

import com.cgi.api.dao.entity.AbstractBaseDocument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class GroceryDocumentListener extends AbstractMongoEventListener<AbstractBaseDocument> {

    public void onBeforeConvert(BeforeConvertEvent<AbstractBaseDocument> event) {
        AbstractBaseDocument document = event.getSource();
        if(null == document.getId()) {
            document.setId(UUID.randomUUID());
        }
    }
}
