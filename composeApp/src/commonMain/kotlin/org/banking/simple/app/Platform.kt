package org.banking.simple.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform