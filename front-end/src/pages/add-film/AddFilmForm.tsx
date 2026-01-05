import { useState, type FormEvent } from "react";
import Form from "../../components/Form";
import Input from "../../components/fields/Input";
import SubmitButton from "../../components/SubmitButton";
import TextArea from "../../components/fields/TextArea";
import ListInput from "../../components/fields/ListInput";
import FileInput from "../../components/fields/FileInput";

const API_URL = import.meta.env.VITE_API_URL;

export default function AddFilmForm() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [director, setDirector] = useState("");
  const [studio, setStudio] = useState("");
  const [genres, setGenres] = useState<string[]>([]);
  const [file, setFile] = useState<File>();

  async function handlaSubmit(e: FormEvent) {
    e.preventDefault();
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
      }

      console.log(response.status)
    } catch (error) {
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

  return (
    <Form title="Add film" onSubmit={handlaSubmit}>
      <div className="fields">
        <Input
          name="Title"
          placeholder="Enter film title"
          onChange={setTitle}
        />
        <Input
          name="Director"
          placeholder="Enter the director"
          onChange={setDirector}
        />
        <Input
          name="Studio"
          placeholder="Enter the studio"
          onChange={setStudio}
        />


        <TextArea
          name="Description"
          placeholder="Enter film description"
          onChange={setDescription}
        />

        <ListInput
          name="Genres"
          placeholder="Enter the genres"
          onChange={setGenres}
          hints={["Fantasy", "Comedy", "Romance", "Action", "Show"]}
        />

        <FileInput
          name="Poster"
          onChange={setFile}
        />
      </div>

      <SubmitButton>ADD</SubmitButton>

      {/* <span>title: {title}</span> */}
    </Form>
  );
}
