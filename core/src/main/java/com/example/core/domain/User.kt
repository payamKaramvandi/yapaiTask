package com.example.core.domain

import java.io.Serializable
import java.util.*


data class User(
    var id: String,
    var username: String,
    var isAdmin: Boolean,
    var isPro: Boolean,
    var iconFarm: Int = 0,
    var iconServer: Int = 0,
    var realName: String,
    var location: String,

    /**
     * @param pathAlias
     * the pathAlias to set
     */
    var pathAlias: String,
    var photosFirstDate: Date,
    var photosFirstDateTaken: Date,

    /**
     * The Date, when a User has favourited a Photo.<br></br>
     * This value is set, when a User is created by
     * [com.googlecode.flickrjandroid.photos.PhotosInterface.getFavorites]
     */
    var faveDate: Date,
    var photosCount: Int = 0,


    var filesizeMax: Long = 0,
    var mbox_sha1sum: String,

    /**
     * @param photosurl
     * the photosurl to set
     */
    var photosurl: String,

    /**
     * @param profileurl
     * the profileurl to set
     */
    var profileurl: String,

    /**
     * @param mobileurl
     * the mobileurl to set
     */
    var mobileurl: String,
    var isRevContact: Boolean = false,
    var isRevFriend: Boolean = false,
    var isRevFamily: Boolean = false,

    ) : Serializable