
import { Card } from '../ui/card'

export const Container = ({
    children,
}: Readonly<{
    children: React.ReactNode
}>)=>{
  return (
    <div className='flex w-full p-4'>

    <div className='w-full flex justify-center'>
        <Card className='w-3/4 p-4'>{children}</Card>
    </div>
</div>
  )
}

