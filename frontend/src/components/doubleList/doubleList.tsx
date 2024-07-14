import { ReactNode, useState } from 'react'
import DoubleListItem from './doubleListUtils/doubleListItem';

export type ListData = { label?: ReactNode | string; content?: ReactNode | string }

interface DoubleListProps {
	listData: ListData[]
}

const DoubleList = ({ listData,...props }: DoubleListProps) => {
	return (
		<div className='flex flex-col justify-around h-full text-lg' {...props} >
			{listData.map((listItem: ListData, index: number) => {
				return (
					<DoubleListItem
						key={index}
						label={listItem.label}
						content={listItem.content}
					/>
				)
			})}
		</div>
	)
}

export default DoubleList