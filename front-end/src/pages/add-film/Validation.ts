/**
 * This module provides validation functions for adding film attributes.
 * It's written without external libraries for educational purposes.
 */

function validateLength(fieldName: string, value: string, minLength: number, maxLength: number): string[] {
    const errors = [];
    if (value.trim().length < minLength) {
        errors.push(`${fieldName} must be at least ${minLength} characters long.`);
    }
    if (value.trim().length > maxLength) {
        errors.push(`${fieldName} must be no more than ${maxLength} characters long.`);
    }
    return errors;
}

export function validateTitle(title: string): string[] {
    return validateLength("Title", title, 3, 100);
}

export function validateDescription(description: string): string[] {
    return validateLength("Description", description, 0, 350);
}

export function validateDirector(director: string): string[] {
    return validateLength("Director", director, 3, 50);
}

export function validateStudio(studio: string): string[] {
    return validateLength("Studio", studio, 2, 50);
}


function validateGenre(genre: string): string[] {
    return validateLength("Genre", genre, 2, 25);
}

export function validateGenres(genres: string[]): string[] {
    const errors: string[] = [];

    for (const genre of genres) {
        const genreErrors = validateGenre(genre);
        // Ensorue no duplicate errors
        if (genreErrors.length > 0) {
            for (const err of genreErrors) {
                if (!errors.includes(err)) {
                    errors.push(err);
                }
            }
        }
    }

    return errors;
}

export function validateFile(file: File | undefined): string[] {
    const errors = [];

    if (!file) {
        return []
    }

    const maxSizeInBytes = 10 * 1024 * 1024; // 10 MB
    if (file.size > maxSizeInBytes) {
        errors.push("File size must be less than 10 MB.");
    }

    return errors;
}