fastlane documentation
----

# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```sh
xcode-select --install
```

For _fastlane_ installation instructions, see [Installing _fastlane_](https://docs.fastlane.tools/#installing-fastlane)

# Available Actions

## Android

### android test

```sh
[bundle exec] fastlane android test
```

Runs all the tests

### android tag_commit_if_needed

```sh
[bundle exec] fastlane android tag_commit_if_needed
```



### android test_update_version

```sh
[bundle exec] fastlane android test_update_version
```

Test update version

### android distribute

```sh
[bundle exec] fastlane android distribute
```

distribute to app distribution

### android get_commits_from_last_tag

```sh
[bundle exec] fastlane android get_commits_from_last_tag
```



### android init_origin

```sh
[bundle exec] fastlane android init_origin
```

Init origin

----

This README.md is auto-generated and will be re-generated every time [_fastlane_](https://fastlane.tools) is run.

More information about _fastlane_ can be found on [fastlane.tools](https://fastlane.tools).

The documentation of _fastlane_ can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
