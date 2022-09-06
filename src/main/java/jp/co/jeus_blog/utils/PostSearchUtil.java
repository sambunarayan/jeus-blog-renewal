package jp.co.jeus_blog.utils;

import jp.co.jeus_blog.dto.PostResponseDto;

import java.util.List;

public final class PostSearchUtil {

    public static int binarySearch(List<PostResponseDto> list, int s, int e, long id) {
        if (s > e) {
            return -1;
        }
        int mid = (s + e) / 2;
        if (list.get(mid).getId() == id) {
            return mid;
        } else {
            int idx = binarySearch(list, s, mid - 1, id);
            if (idx == -1) {
                return binarySearch(list, mid + 1, e, id);
            } else {
                return idx;
            }
        }
    }
}
