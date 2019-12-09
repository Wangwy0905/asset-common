package com.weshare.asset.common.util;

import com.weshare.asset.common.exception.AssetException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class CsvUtilsTest {

    @Test
    public void base64ToCsv() throws AssetException {
        String base64 = "xfq0zrrFLMrWu/q6xSzQ1cP7LMntt93WpLrFLMO9zOXAtNS0LMP7taWz2LHgusUNCjExLDEyMzQ1Njc4OTYzLCw0MTIzNDEyMjMsamF2YSxNMDENCjExLCzV1LuqtaQsRjEyMzUyMTVBTFNFLGMrKyxQMDINCjExLDEyMzQ1Njc4OTIzLNDy0dQsRkFMUzIzNTQ1MzQ1RSzHsLbLLEMwMw0KMTEsMTIzNDU2LLr6uOgsd2Vxd2VweXRob24scHl0aG9uLEMwMw0KMTEsMTIzNDU2Nzg5NjMsu/S9qLuqLDEzMTIzMTIzLGMrKyxNMDENCjExLDEyMzQ1Njc4OTIzLLvGsrMsMTIzMTIzMTAwMCwxMjMyLE0wMQ0KMTEsMTIzNDU2Nzg5MjMsu8bA2iwxMjM0MTIzNDEyNCwxMjMyLE0wMQ0K";
        List<String> resultList = CsvUtils.base64ToList(base64);
        Assert.assertEquals(resultList.size(),8);
    }
}
