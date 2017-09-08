package io.dj.common.utils

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/8
 **/
open class PasswordUtils {

    val HASH_INTERATIONS = 1024
    val SALT_SIZE = 8

    /**
     * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
     */
     open fun entryptPassword(plainPassword: String): String {
        val plain = Encodes.unescapeHtml(plainPassword)
        val salt = Digests.generateSalt(SALT_SIZE)
        val hashPassword = Digests.sha1(plain.toByteArray(), salt, HASH_INTERATIONS)
        return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword)
    }

    /**
     * 验证密码
     * @param plainPassword 明文密码
     * *
     * @param password 密文密码
     * *
     * @return 验证成功返回true
     */
    fun validatePassword(plainPassword: String, password: String): Boolean {
        val plain = Encodes.unescapeHtml(plainPassword)
        val salt = Encodes.decodeHex(password.substring(0, 16))
        val hashPassword = Digests.sha1(plain.toByteArray(), salt, HASH_INTERATIONS)
        return password == Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword)
    }
}