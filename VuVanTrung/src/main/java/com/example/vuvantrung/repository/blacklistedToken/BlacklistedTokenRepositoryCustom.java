package com.example.vuvantrung.repository.blacklistedToken;

import com.example.vuvantrung.entity.BlacklistedToken;
import java.util.List;

 interface BlacklistedTokenRepositoryCustom {
    void deleteExpiredTokensDSL();
    // Bạn có thể thêm các phương thức tùy chỉnh khác ở đây nếu cần
}
