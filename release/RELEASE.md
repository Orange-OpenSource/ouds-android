# OUDS Android release guide

This file lists all the steps to follow when releasing a new version of OUDS Android.

- [Prepare release](#prepare-release)
- [Release](#release)
    * [Publish release to Maven Central](#publish-release-to-maven-central)
    * [Publish release to GitHub](#publish-release-to-github)
    * [Publish release to Google Play Store](#publish-release-to-google-play-store)<br /><br />

## Prepare release

- Create a branch named `prepare-release` to prepare the new release for OUDS Android version X.Y.Z.

- Switch to this branch and launch the `prepareRelease` Gradle task:

    ```shell
    ./gradlew prepareRelease
    ```

  This task finds the next semantic version based on conventional commits and performs the following changes to the project:

    - Update `version` project property in `gradle.properties`.
    - Update version of OUDS Android dependencies in various Markdown files.
    - Increment the app `versionCode` in associated `build.gradle.kts` file.
    - Update content of `CHANGELOG.md`.
    - Archive the documentation in `docs/previousDocVersions/X.Y.Z`.

  You can optionally launch `prepareRelease` with a version property to force the release version:

    ```shell
    ./gradlew prepareRelease -Pversion=X.Y.Z
    ```

- Verify the changes mentioned above, then commit and push.

- Create a new pull request named `chore: prepare release X.Y.Z` on GitHub to merge your branch into `develop`.

- Review and merge this pull request on GitHub.<br /><br />

## Release

- Create a new pull request named `chore: release X.Y.Z` on GitHub to merge `develop` into `main`.

- Review and merge this pull request on GitHub.

- Switch to the latest main commit and launch the `tagRelease` Gradle task:

    ```shell
    ./gradlew tagRelease
    ```

  This task adds an `X.Y.Z` tag and push it to the remote repository.<br /><br />

### Publish release to Maven Central

- Go to [GitHub Actions](https://github.com/Orange-OpenSource/ouds-android/actions) and open the workflow launched by the tag creation.

- Click `Review deployments`, select `maven-central-release` and click `Approve and deploy`.

  ![Maven Central release deployment](images/maven_central_release_01.png)

- Go to [Central Publisher Portal](https://central.sonatype.com/publishing) and verify the content of the OUDS Android deployment.

  ![Central Publisher Portal](images/maven_central_release_02.png)

- Launch the `testCentralPublisherPortalDeployment` Gradle task using your user token as parameter:

    ```shell
    ./gradlew testCentralPublisherPortalDeployment -PcentralPublisherPortalToken=<token>
    ```

  This task allows you to test the release before it is deployed to Maven Central and performs the following changes to the project:

    - Add Central Publisher Portal Maven repository for manual testing.
    - Remove published Android Studio modules from `settings.gradle.kts`.
    - Replace project dependencies with artifact dependencies in `build.gradle.kts` files of non published modules.
    - Replace project dependencies used for Dokka with artifact dependencies in root `build.gradle.kts`.

- Synchronize Gradle, build app, deploy and test on device.

- Go back to Central Publisher Portal and click `Publish` if everything is OK or `Drop` otherwise.<br /><br />

### Publish release to GitHub

- Go to [GitHub Actions](https://github.com/Orange-OpenSource/ouds-android/actions), open the release workflow, click `Review deployments`, select
  `github-release` and click `Approve and deploy`.

- Go to [GitHub Releases](https://github.com/Orange-OpenSource/ouds-android/releases).

- Edit the new release draft.

  ![Edit GitHub release](images/github_release_01.png)

- Set previous tag and click `Generate release notes` to automatically generate the release notes.

  ![Generate GitHub release notes](images/github_release_02.png)

- Remove lines starting with `* chore`. You can use `sed` command line tool for instance:

  ```shell
  sed '/^* chore/d' release_notes.txt
  ```

- Verify the release notes using the `Preview` tab.

- Optionally check `Set as a pre-release` and click `Publish release`.<br /><br />

### Publish release to Google Play Store

- Go to [GitHub Actions](https://github.com/Orange-OpenSource/ods-android/actions), open the release workflow, click `Review deployments`, select
  `google-play-store-release` and click `Approve and deploy`.

- Go to [OMA Portal](https://oma-portal.orange.fr) and open the `Design System Toolbox [OUDS Demo]` dashboard.

- Click `Integrations`, find the uploaded bundle in the `Artifacts` sections and click `Create release`.

  ![Create OMA Portal release](images/google_play_store_release_01.png)

- Set `Release type` to `Beta (Open or Closed)` and click `Submit`.

  ![Submit OMA Portal release](images/google_play_store_release_02.png)

- Progress through the various submission steps.

- When step is `Store submissions`, select `Closed beta with Google Groups`, set `Email addresses` to `design-toolbox-android@googlegroups.com` and click
  `Go for store submission`.

  ![OMA Portal store submission](images/google_play_store_release_03.png)<br /><br />

- Click on `Go to publish` when step is `Store publication`. This is the last manual action required to publish the release.

  ![OMA Portal store publication](images/google_play_store_release_04.png)
