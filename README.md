# petra-stdlib

`petra-stdlib` is an intentionally lightweight, polyglot standard library providing verified collections, abstract data structures (ADTs), concurrency and verification primitives, tailored for programs built with the **Petra Coord/Base** paradigm.

Because **Petra Coord / Base** are already defined as clean Object-Oriented Programming (OOP) language subsets—enforced by tools like [`petra-lsp`](https://github.com/petracode-labs)—the core language rules remain minimal. `petra-stdlib` complements this by offering pre-verified data structures and abstractions ready for formal verification across multiple target languages.

For full background on the Petra paradigm, see the [What is Petra? Guide](https://docs.petracode.co.uk/guides/whatispetra/).

---

## Key Features

- **Intentionally Lightweight:** Contains only essential verified primitives to keep computational verification overhead to a minimum.
- **Verified Collections & ADTs:** Standardised, formally verified collections (lists, maps, sets) and abstract data structures for reachability and protocol verification.
- **Polyglot Roadmap:** Initial core focus on **Java** and **C++**, expanding outward to additional mainstream and systems languages. The intention is to align implementations across all supported languages, which is possible as **Petra Coord / Base** are simplified subsets of OOP subsets. 
- **Ecosystem Requirement:** Functions as a required dependency across `petra-examples` and related Petra modules.

---

## Getting Started

`petra-stdlib` is required for building and running projects within the Petra ecosystem, including `petra-examples`.

### Java / Maven Setup

First, build and install `petra-stdlib` to your local repository:

```bash
mvn clean install
```

Then, add the following dependency to the `pom.xml` of your `petra-examples` project (or any Petra-based Java project):

```xml
<dependency>
    <groupId>com.cognitionbox.petra.stdlib</groupId>
    <artifactId>petra-stdlib</artifactId>
    <version>0.0.2</version>
</dependency>
