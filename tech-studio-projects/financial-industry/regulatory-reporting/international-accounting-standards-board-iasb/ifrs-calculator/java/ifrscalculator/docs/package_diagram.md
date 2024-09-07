shell


### `package_diagram.md`

```markdown
# Package Diagram

The package diagram shows the organization of packages and their dependencies.

+----------------------------+
com.java.ifrscalculator
- main
- calculations
- logging
- errorhandling
- profiling
+----------------------------+

markdown

    |      
    v

+----------------------------+
com.java.ifrscalculator.logging
- LoggingManager
+----------------------------+

markdown

    |
    v

+----------------------------+
com.java.ifrscalculator.errorhandling
- ErrorHandler
+----------------------------+

markdown

    |
    v

+----------------------------+
com.java.ifrscalculator.profiling
- ProfilingManager
+----------------------------+