import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import App from './App.tsx'
import AddFilm from './pages/add-film/AddFilm.tsx'

import './index.css'
import Home from './pages/home/Home.tsx'

const router = createBrowserRouter([
  {
    path: "/", 
    element: <App/>,
    children: [
      { path: "/", element: <Home></Home> },
      { path: "/add-film", element: <AddFilm/> },
      { path: "/profile", element: <h1>Profile page is not ready yet</h1> },
      { path: "*", element: <h1>Woops! 404: page is not found</h1> }
    ]
  }
]);

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <RouterProvider router={router}/>
  </StrictMode>,
)
