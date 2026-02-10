import { Link } from "react-router-dom"

import { API_URL, STORAGE_URL } from "../../common/APIConstants"

import "../../App.css"
import "../../style-utilities/borders.css"
import "../../style-utilities/bright-elements.css"
import "../../components/fields/fields.css"
import "./Home.css"
import { useEffect, useState } from "react"

export default function Home() {

  const [hintInput, setHintInput] = useState('');
  const [hint, setHint] = useState('');
  const [films, setFilms] = useState<FilmCardData[] | null>(null);

  useEffect( () => {
    setFilms(null);
    const startTime = new Date();
    let ignore = false;

    const params = new URLSearchParams({
        pageNumber: "0",
        pageSize: "30"
    });
    if (hint) {
        params.append("hint", hint);
    }
    
    fetch(`${API_URL}/films/catalog?${params.toString()}`)
      .then( (response) => response.json() )
      .then( (data) => {
        if (!ignore) {
            const endTime = new Date();
            console.log("Loaded for:", (endTime.getTime() - startTime.getTime())/1000 );
            setFilms(data);
        }
      })
      .catch( (error) => {
        console.error("Error fetching catalog films:", error);
      });

      return () => {ignore = true}
    
  }, [hint] );

  let filmsResult = null;

  if (films === null) {
    filmsResult = <h2>Loading films...</h2>

  } else if (films.length === 0) {
    filmsResult = <h2>No films found</h2>
    
  } else {
    const filmCards = films.map( (film) => <FilmCard 
        key={film.id}
        id={film.id}
        title={film.title}
        director={film.director}
        company={film.company}
        posterFilename={film.posterFilename}
        genres={film.genres}
    />);
    
    filmsResult = <div className="film-grid">{filmCards}</div>;
  }

  return (
    <>
        <div className="content-box search-bar">
            <form 
                className="search-line"
                onSubmit={ (e) => {
                    e.preventDefault();
                    setHint(hintInput);
                }}
            >
                <input 
                    className="secondary-border accent-hoover accent-focus field-box field-placeholder" 
                    type="text" placeholder="Type here..." 
                    value={hintInput}
                    onChange={ e => { setHintInput(e.target.value) }}                />    

                <button
                    type="submit" 
                    className="button bright-bg-hoover search-button"
                >
                    Search
                </button>   
            </form>             
        </div>

        <div className="film-catalog">
            {filmsResult}
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
            key={item} className="secondary-border film-card-tag">{item[0].toUpperCase() + item.slice(1)}
        </div>

    );

    return <Link to={`/films/${id}`} className="primary-border film-card">

        <img 
            className="film-card-image"
            src={STORAGE_URL + "/" + posterFilename}
            alt="Film Poster"
        />

        <h3 className="film-card-title">{title}</h3>

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

