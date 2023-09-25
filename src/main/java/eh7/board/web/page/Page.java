package eh7.board.web.page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page {
    private int currentPage;
    private int totalPages;
    private int totalContents;
    private int size;
    private int navSize;
    private int startPage;
    private int endPage;
    private boolean hasPrev;
    private boolean hasNext;

    public Page(int currentPage, int totalPages, int totalContents, int navSize, int startPage, int endPage, boolean hasPrev, boolean hasNext) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalContents = totalContents;
        this.size = PageConst.PAGE_SIZE;
        this.startPage = startPage;
        this.endPage = endPage;
        this.navSize = navSize;
        this.hasPrev = hasPrev;
        this.hasNext = hasNext;
    }
}
