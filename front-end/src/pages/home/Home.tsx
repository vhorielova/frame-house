import { Link } from "react-router-dom"

import { API_URL, STORAGE_URL } from "../../common/APIConstants"

import "../../App.css"
import "../../style-utilities/borders.css"
import "../../style-utilities/bright-elements.css"
import "../../components/fields/fields.css"
import "./Home.css"
import { useEffect, useState } from "react"

export default function Home() {

  const startFilmData: FilmCardData[] = [
    {
        id: -1,
        title: "Example Film",
        director: "Dir Examplestain",
        company: "Example Production",
        posterFilename: "unexisting-poster.png",
        genres: ["Example", "Hardcoded", "Three tags"]
    },
    {
        id: -2,
        title: "Test Film",
        director: "Dir Testenko",
        company: "Test Production",
        posterFilename: "unexisting-poster.png",
        genres: ["Test", "Hardcoded"]
    }
  ]

  const [films, setFilms] = useState<FilmCardData[]>(startFilmData);

  useEffect( () => {
    fetch(`${API_URL}/films/catalog?pageNumber=0&pageSize=10`)
      .then( (response) => response.json() )
      .then( (data) => {
        setFilms(data);
      })
      .catch( (error) => {
        console.error("Error fetching catalog films:", error);
      });
    
  }, [] );

  const filmCards = films.map( (film) => 
    <FilmCard 
        key={film.id}
        id={film.id}
        title={film.title}
        director={film.director}
        company={film.company}
        posterFilename={film.posterFilename}
        genres={film.genres}
    />
  );

  return (
    <>
        <div className="content-box search-bar">
            <div className="search-line">
                <input 
                    className="secondary-border accent-hoover accent-focus field-box field-placeholder" 
                    type="text" placeholder="Type here..." 
                />    

                <button type="button" className="button bright-bg-hoover search-button">
                    Search
                </button>   
            </div>             
        </div>

        <div className="film-catalog">
            {filmCards}
        </div>
    </>
  );
}

interface FilmCardData {
    id: number;
    title: string;
    director: string;
    company: string;
    posterFilename: string;
    genres: string[];
}


export function FilmCard( { id, title, director, company, posterFilename, genres }: FilmCardData ) {

    const genreItems = genres.map( 
        item => <div 
            key={item} className="secondary-border film-card-tag">{item}
        </div>

    );

    return <Link to={`/films/${id}`} className="primary-border film-card">

        <img 
            className="film-card-image"
            src={"https://creativereview.imgix.net/uploads/2023/12/Oppenheimer.jpg?auto=compress,format&crop=faces,entropy,edges&fit=crop&q=60&w=1263&h=2000" /*STORAGE_URL + "/" + posterFilename*/} 
            alt="Film Poster"
        />

        <div><h3 className="film-card-title">{title}</h3></div>

        <div className="film-card-origin">
            By: <b>{director}</b>
            <br/>
            <b>{company}</b>
        </div>
        
        <div className="film-card-genres">
            {genreItems}
        </div> 

    </Link>
}

