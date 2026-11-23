package org.cloudburstmc.protocol.bedrock.data.book;

import lombok.Data;

/**
 * ReplacePage operation, sent when a page should be replaced. See BookEditPacket
 */
@Data
public class ReplacePage implements BookEditAction {

    @Override
    public BookEditOperation getType() {
        return BookEditOperation.REPLACE_PAGE;
    }


    /**
     * Page replacement index
     */
    private int pageIndex;
    /**
     * The new page text
     */
    private String pageText;
    /**
     * Name of the new photo, edu only
     */
    private String photoName;
}