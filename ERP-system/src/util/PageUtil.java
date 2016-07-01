package util;

/*
 * 分页辅助类
 */
public class PageUtil {
	public static Page getPage(int everyPage, int totalCount, int currentPage) {
		int totalPage = getTotalPage(everyPage, totalCount);
		int beginIndex = getBeginIndex(everyPage, currentPage);
		boolean hasPrePage = getHasPrePage(currentPage);
		boolean hasNextPage = getHasNextPage(everyPage, totalCount, currentPage);
		return new Page(everyPage, totalPage, totalCount, currentPage, beginIndex, hasPrePage, hasNextPage);
	}

	// 得到总页数
	private static int getTotalPage(int everyPage, int totalCount) {
		if (totalCount != 0 && totalCount % everyPage == 0) {
			return totalCount / everyPage;
		} else {
			return totalCount / everyPage + 1;
		}
	}

	// 得到本页消息起始编号
	private static int getBeginIndex(int everyPage, int currentPage) {
		return (currentPage - 1) * everyPage;
	}

	// 判断是否有上一页
	private static boolean getHasPrePage(int currentPage) {
		return currentPage != 1;
	}

	// 判断是否有下一页
	private static boolean getHasNextPage(int everyPage, int totalCount, int currentPage) {
		int totalPage = getTotalPage(everyPage, totalCount);
		return currentPage != totalPage;
	}
}
