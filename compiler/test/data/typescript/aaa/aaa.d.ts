declare interface InvariantBox<T> {}

declare interface BaseEvent {
  queryByReturnType(query: string, parameters?: any[]): InvariantBox<any>;
}

declare class BoxStringEvent implements BaseEvent {
  queryByReturnType(query: string, parameters?: any[]): InvariantBox<string>;
}
