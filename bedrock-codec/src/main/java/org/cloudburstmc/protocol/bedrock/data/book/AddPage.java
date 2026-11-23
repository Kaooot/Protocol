package org.cloudburstmc.protocol.bedrock.data.book;

import lombok.Data;

/**
 * AddPage operation sent within the BookEditPacket. Indicates that a new page should be added
 */
@Data
public class AddPage implements BookEditAction {

    @Override
    public BookEditOperation getType() {
        return BookEditOperation.ADD_PAGE;
    }


    /**
     * The index of the page which should be added
     */
    private int pageIndex;
    /**
     * Text of the added page
     */
    private String pageText;
    /**
     * Name of the photo, edu only
     */
    private String photoName;
}