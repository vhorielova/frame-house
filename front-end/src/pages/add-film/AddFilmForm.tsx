import { useState, type FormEvent } from "react";
import Form from "../../components/Form";
import Input from "../../components/fields/Input";
import SubmitButton from "../../components/SubmitButton";
import TextArea from "../../components/fields/TextArea";
import ListInput from "../../components/fields/ListInput";
import FileInput from "../../components/fields/FileInput";

import { 
  validateTitle, 
  validateDescription, 
  validateStudio, 
  validateGenres, 
  validateFile, 
  validateDirector
} from "./Validation";
import toast from "react-hot-toast";



const API_URL = import.meta.env.VITE_API_URL;

export default function AddFilmForm() {

  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [director, setDirector] = useState("");
  const [studio, setStudio] = useState("");
  const [genres, setGenres] = useState<string[]>([]);
  const [file, setFile] = useState<File>();

  const [titleErrors, setTitleErrors] = useState<string[]>([]);
  const [descriptionErrors, setDescriptionErrors] = useState<string[]>([]);
  const [directorErrors, setDirectorErrors] = useState<string[]>([]);
  const [studioErrors, setStudioErrors] = useState<string[]>([]);
  const [genresErrors, setGenresErrors] = useState<string[]>([]);
  const [fileErrors, setFileErrors] = useState<string[]>([]);
  async function handlaSubmit(e: FormEvent) {
    e.preventDefault();

    if (!validateForm()) {
      return;
    }

    console.log(`Title: ${title}`);
    console.log(`Genre: ${description}`);
    console.log(`Director: ${director}`);
    console.log(`Studio: ${studio}`);
    console.log(`Genres: ${genres}`);
    console.log(`File: ${file}`);

    const formData = new FormData();
    
    if (file) {
      formData.append("file", file);
    }

    const filmData = {
      title,
      description,
      director,
      company: studio,
      genres
    }
    const jsonBlob = new Blob([JSON.stringify(filmData)], { 
      type: 'application/json' 
    });

    formData.append("request", jsonBlob);

    try {

      const response = await fetch(API_URL + "/films", {
        method: "POST",
        body: formData
      });

      if (response.ok) {
        clearForm(); 
        toast.success("Film added successfully!", {
          duration: 5000,
          className: "toast", 
        }); 
      }

      console.log(response.status)
    } catch (error) {
      toast.error("Film was not added.", {
        duration: 5000,
        className: "toast", 
      });
      console.error("Error:", error);
    }
  }

  function clearForm() {
    setTitle('');
    setDescription('');
    setDirector('');
    setStudio('');
    setGenres([]);
    setFile(undefined);
  }

  function validateForm() {
    let hasErrors = false;

    const fieldAndValidations = [
      { value: title, validator: validateTitle, setter: setTitleErrors },
      { value: description, validator: validateDescription, setter: setDescriptionErrors },
      { value: director, validator: validateDirector, setter: setDirectorErrors },
      { value: studio, validator: validateStudio, setter: setStudioErrors },
    ];

    for (const { value, validator, setter } of fieldAndValidations) {
      const errors = validator(value);
      setter(errors);
      if (errors.length > 0) {
        hasErrors = true;
      }
    }

    const genresValidationErrors = validateGenres(genres);
    setGenresErrors(genresValidationErrors);
    if (genresValidationErrors.length > 0) {
      hasErrors = true;
    }
    
    const fileValidationErrors = validateFile(file);
    setFileErrors(fileValidationErrors);
    if (fileValidationErrors.length > 0) {
      hasErrors = true;
    }

    return !hasErrors;
  }

  return (
    <Form title="Add film" onSubmit={handlaSubmit}>
      <div className="fields">
        <Input
          name="Title"
          placeholder="Enter film title"
          onChange={setTitle}
          errors={titleErrors}
        />
        <Input
          name="Director"
          placeholder="Enter the director"
          onChange={setDirector}
          errors={directorErrors}
        />
        <Input
          name="Studio"
          placeholder="Enter the studio"
          onChange={setStudio}
          errors={studioErrors}
        />


        <TextArea
          name="Description"
          placeholder="Enter film description"
          onChange={setDescription}
          errors={descriptionErrors}
        />

        <ListInput
          name="Genres"
          placeholder="Enter the genres"
          onChange={setGenres}
          hints={["Fantasy", "Comedy", "Romance", "Action", "Show"]}
          errors={genresErrors}
        />

        <FileInput
          name="Poster"
          onChange={setFile}
          errors={fileErrors}
        />
      </div>

      <SubmitButton>ADD</SubmitButton>

      {/* <span>title: {title}</span> */}
    </Form>
  );
}
