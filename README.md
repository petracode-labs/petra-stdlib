# petra-stdlib

`petra-stdlib` is a polyglot standard library designed to standardise core programming components for efficient formal verification, based on the **Petra Coord/Base** approach to Object-Oriented Programming (OOP).

By decoupling high-level protocol coordination (`Coord`) from low-level state and primitive execution (`Base`), `petra-stdlib` establishes unified behavioral contracts and verification primitives across multiple programming languages.

---

## Key Features

- **Coord/Base Architecture:** Implements standard Petra paradigms to separate control-flow protocol verification from foundational state management.
- **Verification-First Design:** Standardised abstractions crafted specifically to support automated formal verification tools and reachability proofs.
- **Polyglot Roadmap:** Initial core implementations focused on **Java** and **C++**, with planned expansions to additional mainstream and systems languages.
- **Ecosystem Integration:** Functions as the core standard library required across Petra example projects, domain modules, and verification engines.

---

## Getting Started

`petra-stdlib` is required for building and running projects within the Petra ecosystem, such as `petra-examples`.

### Java / Maven Setup

Add the following dependency to the `pom.xml` of your `petra-examples` project (or any Petra-based Java project):

```xml
<dependency>
    <groupId>com.cognitionbox.petra.stdlib</groupId>
    <artifactId>petra-stdlib</artifactId>
    <version>0.0.2</version>
</dependency>
```

---

## The Petra Coord/Base Paradigm

The Petra programming model structures software into distinct coordination and base components to drastically simplify formal verification:

1. **Coord (Coordinators):** Protocol-driven objects responsible for orchestrating execution flows, concurrency invariants, and state transition contracts without managing mutable state directly.
2. **Base (Primitives & State):** Verified foundational data structures and primitive execution components that encapsulate concrete state and mathematical invariants.

By standardising these abstractions inside `petra-stdlib`, programs written in supported languages can be formally verified with low computational overhead and consistent semantic behavior.

---

## Language Support Matrix

| Language | Status | Focus Area |
| :--- | :--- | :--- |
| **Java** | `v0.0.2` (Active) | Core standard library & verification primitives |
| **C++** | Active | High-performance verified primitives & bindings |
| **Rust** | Planned | Memory-safe Coord/Base runtime primitives |
| **Python** | Planned | Verification wrappers & dynamic execution models |

---

## Ecosystem & References

For documentation, research background, and related repositories across the Petra ecosystem:

- **GitHub Organization:** [github.com/petracode-labs](https://github.com/petracode-labs)
- **Official Website:** [www.petracode.co.uk](https://www.petracode.co.uk)

---

## License

This project is licensed under the terms specified in the [`LICENSE`](./LICENSE) file.