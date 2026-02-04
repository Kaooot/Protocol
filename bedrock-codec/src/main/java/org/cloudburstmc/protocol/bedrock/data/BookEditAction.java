package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Kaooot
 */
public abstract class BookEditAction {

    public abstract BookEditOperation getType();

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class ReplacePage extends BookEditAction {
        private int pageIndex;
        private String pageText;
        private String photoName;

        @Override
        public BookEditOperation getType() {
            return BookEditOperation.REPLACE_PAGE;
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class AddPage extends BookEditAction {
        private int pageIndex;
        private String pageText;
        private String photoName;

        @Override
        public BookEditOperation getType() {
            return BookEditOperation.ADD_PAGE;
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class DeletePage extends BookEditAction {
        private int pageIndex;

        @Override
        public BookEditOperation getType() {
            return BookEditOperation.DELETE_PAGE;
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class SwapPages extends BookEditAction {
        private int pageIndex;
        private int swapWithIndex;

        @Override
        public BookEditOperation getType() {
            return BookEditOperation.SWAP_PAGES;
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class Finalize extends BookEditAction {
        private String title;
        private String author;
        private String xuid;

        @Override
        public BookEditOperation getType() {
            return BookEditOperation.FINALIZE;
        }
    }
}