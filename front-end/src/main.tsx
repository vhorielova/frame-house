import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import App from './App.tsx'
import AddBook from './pages/add-book/AddBook.tsx'

import './index.css'

const router = createBrowserRouter([
  {
    path: "/", 
    element: <App/>,
    children: [
      { path: "/", element: <h1>Wellcome to Frame House</h1> },
      { path: "/add-film", element: <AddBook/> },
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
