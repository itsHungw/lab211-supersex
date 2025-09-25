package model;

/**
 * Defines the user roles in the system.
 * - ADMIN: Has full access to all system functionalities.
 * - USER: Represents a student with limited access, can only view/update their own information.
 */
public enum Role {
    ADMIN,
    USER
}
