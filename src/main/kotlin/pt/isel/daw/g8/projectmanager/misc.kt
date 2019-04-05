package pt.isel.daw.g8.projectmanager

import org.slf4j.Logger
import org.slf4j.LoggerFactory

//TODO log useful information
inline fun <reified T : Any> loggerFor(): Logger = LoggerFactory.getLogger(T::class.java)