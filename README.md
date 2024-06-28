# Password Manager App

A simple and secure Password Manager application built with Jetpack Compose for the user interface and Room for local data storage. This application allows users to securely store, manage, and retrieve their passwords.

## Description

The Password Manager App is designed to help users manage their passwords efficiently. It encrypts passwords before storing them in a local database, ensuring that user data is secure. Users can add, edit, and delete their account information with ease.

## Features

- Add new account with encrypted password
- Edit existing account details
- Delete account information
- Show/Hide passwords with a toggle
- Secure encryption and decryption of passwords

## Installation

To get a local copy up and running, follow these simple steps:

### Prerequisites

- Android Studio installed on your machine
- Minimum Android SDK version: 21

### Steps

1. **Clone the repository**

    ```sh
    git clone  https://github.com/saurabhchaturvedi75/Password-Manager-App
    ```

2. **Open the project in Android Studio**

    - Launch Android Studio
    - Select `Open an existing Android Studio project`
    - Navigate to the directory where you cloned the repository and select it

3. **Build the project**

    - Let Android Studio download and configure all dependencies
    - Build the project by selecting `Build > Make Project`

4. **Run the app**

    - Connect an Android device or start an emulator
    - Click on the `Run` button in Android Studio

## Usage

### Adding a New Password

1. Click on the `+` button.
2. Fill in the account type, username, and password fields.
3. Click `Save` to store the password securely.

### Editing an Existing Password

1. Click on the edit icon next to the account you want to edit.
2. Modify the account type, username, or password.
3. Click `Update` to save the changes.

### Deleting a Password

1. Click on the edit icon next to the account you want to delete.
2. Click the `Delete` button.

### Show/Hide Password

- Click on the eye icon next to the password field to toggle between showing and hiding the password.

## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

Distributed under the MIT License. See `LICENSE` for more information.

## Acknowledgments

- [Jetpack Compose](https://developer.android.com/jetpack/compose) - For the UI components
- [Room](https://developer.android.com/jetpack/androidx/releases/room) - For the local database
- [Bouncy Castle](https://www.bouncycastle.org/) - For the cryptographic functions
- [Material Icons](https://material.io/resources/icons/) - For the icons used in the app

---

Thank you for using the Password Manager App! If you have any questions or feedback, feel free to open an issue or contact the repository owner.
