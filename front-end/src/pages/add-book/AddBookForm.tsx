import { useState } from "react";
import Form from "../../components/Form";
import Input from "../../components/fields/Input";
import Button from "../../components/Button";
import TextArea from "../../components/fields/TextArea";
import ListInput from "../../components/fields/ListInput";

export default function AddBookForm() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [director, setDirector] = useState("");
  const [studio, setStudio] = useState("");
  const [genres, setGenres] = useState<string[]>([]);

  function handlaSubmit() {
    console.log(`Title: ${title}`);
    console.log(`Genre: ${description}`);
    console.log(`Director: ${director}`);
    console.log(`Studio: ${studio}`);
    console.log(`Genres: ${genres}`)
  }

  return (
    <Form title="Add film">
      <div className="fields">
        <Input
          name="Title"
          placeholder="Enter film title"
          onChange={setTitle}
        />
        <TextArea
          name="Description"
          placeholder="Enter film description"
          onChange={setDescription}
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

        <ListInput
          name="Genres"
          placeholder="Enter the genres"
          onChange={setGenres}
          hints={["Fantasy", "Comedy", "Romance", "Action", "Show"]}
        />
      </div>

      <Button onClick={handlaSubmit}>ADD</Button>

      {/* <span>title: {title}</span> */}
    </Form>
  );
}
