package recursiveType

external interface AnimatedValue
external interface Rec {
    var foo: Rec
}
external interface `T$0` {
    @nativeGetter
    operator fun get(key: String): dynamic /* `T$0` | AnimatedValue */
    @nativeSetter
    operator fun set(key: String, value: `T$0`)
    @nativeSetter
    operator fun set(key: String, value: AnimatedValue)
}
external fun foo(): dynamic /* `T$0` | AnimatedValue */ = definedExternally
external fun bar(d: `T$0`): Unit = definedExternally
external fun bar(d: AnimatedValue): Unit = definedExternally
external fun boo(): Rec = definedExternally
external fun baz(d: Rec): Unit = definedExternally
