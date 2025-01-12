package com.majid.jetpackchatapp.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.security.MessageDigest

@Serializable
data class Authorization(
    val email: String,
    val password: String,
    @SerialName("display_name") val displayName: String,
    @SerialName("unique_id") val uniqueId: String,
)

@Serializable
data class Token(
    val access: String,
    val refresh: String,
    @SerialName("token_uuid") val tokenUUID: String,
    @SerialName("refresh_uuid") val refreshUUID: String,
    @SerialName("token_expire") val tokenExpire: String,
    @SerialName("refresh_expire") val refreshExpire: String,
)

enum class AuthenticationCodeType(val value: Int) {
    NEW_USER(1),
    RENEW_PASSWORD(2),
    RESET_PASSWORD(3);

    companion object {
        fun fromInt(value: Int) = AuthenticationCodeType.values().first { it.value == value }
    }
}

@Serializable
data class Verification(
    val email: String,
    val code: String,
    val member: String,
    val password: String,
    @SerialName("code_type") val codeType: AuthenticationCodeType,
)

@Serializable
data class RenewPassword(
    val password: String,
    @SerialName("confirm_password") val confirmPassword: String,
)

@Serializable
data class UpdatePassword(
    val password: String,
    @SerialName("new_password") val newPassword: String,
    @SerialName("confirm_password") val confirmPassword: String,
)

@Serializable
data class Member(
    val id: String,
    val email: String,
    val verified: Boolean,
    val active: Boolean,
    @SerialName("created_at") val createdAt: String
)

@Serializable
data class Profile(
    val id: String,
    val member: String,
    @SerialName("display_name") val displayName: String,
    @SerialName("nick_name") val nickName: String,
    @SerialName("profile_image") val profileImage: String,
    val authorized: Int,
    @SerialName("is_private") val isPrivate: Boolean,
    @SerialName("created_at") val createdAt: String
)

@Serializable
data class Authentication(
    val member: Member,
    val profile: Profile,
    val token: Token
)


fun generateMD5(input: String): String {
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(input.toByteArray())
    return digest.joinToString("") { "%02x".format(it) }
}