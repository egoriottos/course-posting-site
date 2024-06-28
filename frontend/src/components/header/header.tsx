import React from 'react'
import { Button } from '@/components/ui/button'
import { ModeToggle } from '../ui/modelToggle'

export const Header = () => {
  return (
    <header className= "flex justify-between sticky w-full p-4">
        <ModeToggle/>
            <div className= "flex gap-5">
                <Button></Button>
                <Button>О нас</Button>
                <Button>Помощь</Button>
                <Button>Выход</Button>
            </div>
    </header>
  )
}
