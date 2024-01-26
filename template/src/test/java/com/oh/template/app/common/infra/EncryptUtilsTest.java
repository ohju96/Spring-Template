package com.oh.template.app.common.infra;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class EncryptUtilsTest {

    @InjectMocks
    EncryptUtils encryptUtils;

    @Test
    @DisplayName("AES128 CBC 암호화/복호화 테스트")
    public void encryptAndDecryptTest() throws Exception {
        // given
        String targetString = "testq233";
        encryptUtils.setKey("234dkaghgksek123");

        // when
        String encryptAES128CBC = EncryptUtils.encryptAES128CBC(targetString);
        String res = EncryptUtils.decryptAES128CBC(encryptAES128CBC);

        // then
        assertThat(encryptAES128CBC).isNotNull();
        assertThat(targetString).isNotEqualTo(encryptAES128CBC);
        assertThat(res).isEqualTo(targetString);

    }
}