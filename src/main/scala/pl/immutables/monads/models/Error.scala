package pl.immutables.monads.models

sealed trait Error
object InvalidToken extends Error